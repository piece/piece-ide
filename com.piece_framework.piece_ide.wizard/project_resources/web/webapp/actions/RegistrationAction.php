<?php
/* vim: set expandtab tabstop=4 shiftwidth=4: */

/**
 * PHP versions 4 and 5
 *
 * Copyright (c) 2007 Piece Project, All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * @package    Piece_Unity
 * @copyright  Piece Project
 * @license    http://www.opensource.org/licenses/bsd-license.php  BSD License (revised)
 * @version    SVN: $Id$
 * @since      File available since Release 1.0.0
 */

require_once 'Piece/Unity/Service/FlowAction.php';
require_once 'Piece/Unity/Service/FlexyElement.php';

// {{{ RegistrationAction

/**
 * Action class for the flow Registration.
 *
 * @package    Piece_Unity
 * @copyright  Piece Project
 * @license    http://www.opensource.org/licenses/bsd-license.php  BSD License (revised)
 * @version    Release: 1.1.0
 * @since      Class available since Release 1.0.0
 */
class RegistrationAction extends Piece_Unity_Service_FlowAction
{

    // {{{ properties

    /**#@+
     * @access public
     */

    /**#@-*/

    /**#@+
     * @access private
     */

    var $_user;
    var $_flowName;
    var $_useAHAH;

    /**#@-*/

    /**#@+
     * @access public
     */

    function RegistrationAction()
    {
        $this->_user = &new stdClass();
        $this->_useAHAH = $_SERVER['SCRIPT_NAME'] == '/register-with-exclusive-mode-and-ahah.php';
    }

    function doActivityOnProcessConfirmForm()
    {
        $validation = &$this->_context->getValidation();
        if ($validation->validate('Registration', $this->_user)) {
            return 'DisplayConfirmationFromProcessConfirmForm';
        } else {
            return 'DisplayFormFromProcessConfirmForm';
        }
    }

    function doActivityOnProcessRegister()
    {
        return 'DisplayFinishFromProcessRegister';
    }

    function doActivityOnDisplayForm()
    {
        $flexyElement = &new Piece_Unity_Service_FlexyElement();
        $flexyElement->addForm($this->_flow->getView(), $this->_context->getScriptName());
        $flexyElement->restoreValues('Registration', $this->_user);

        $viewElement = &$this->_context->getViewElement();
        $viewElement->setElement('useAHAH', $this->_useAHAH);

        $this->_setTitle();
    }

    function doActivityOnDisplayConfirmation()
    {
        $flexyElement = &new Piece_Unity_Service_FlexyElement();
        $flexyElement->addForm($this->_flow->getView(), $this->_context->getScriptName());

        $viewElement = &$this->_context->getViewElement();
        $viewElement->setElementByRef('user', $this->_user);
        $viewElement->setElement('useAHAH', $this->_useAHAH);

        $this->_setTitle();
    }

    function doActivityOnDisplayFinish()
    {
        $viewElement = &$this->_context->getViewElement();
        $viewElement->setElement('useAHAH', $this->_useAHAH);

        $this->_setTitle();
    }

    function prepare()
    {
        $continuation = &$this->_context->getContinuation();
        $this->_flowName = $continuation->getCurrentFlowName();
    }

    /**#@-*/

    /**#@+
     * @access private
     */

    function _setTitle()
    {
        if ($_SERVER['SCRIPT_NAME'] == '/register-with-non-exclusive-mode.php') {
            $title = 'A.1. An application component for registration. *non-exclusive*';
        } elseif ($_SERVER['SCRIPT_NAME'] == '/register-with-exclusive-mode.php') {
            $title = 'A.2. An application component for registration. *exclusive*';
        } elseif ($_SERVER['SCRIPT_NAME'] == '/register-with-exclusive-mode-and-ahah.php') {
            $title = 'A.3. An application component with AHAH for registration. *exclusive*';
        }

        $viewElement = &$this->_context->getViewElement();
        $viewElement->setElement('title', $title);
    }

    /**#@-*/

    // }}}
}

// }}}

/*
 * Local Variables:
 * mode: php
 * coding: iso-8859-1
 * tab-width: 4
 * c-basic-offset: 4
 * c-hanging-comment-ender-p: nil
 * indent-tabs-mode: nil
 * End:
 */
?>
